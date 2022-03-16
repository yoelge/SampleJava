package myapps;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class Pipe {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("transactional.id", "my-transactional-id");
		Producer<String, String> producer = new KafkaProducer<>(props, new StringSerializer(), new StringSerializer());

		producer.initTransactions();

		try {
			 producer.beginTransaction();
			 for (int i = 0; i < 100; i++)
				 producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)));
			 producer.commitTransaction();
		} catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
			 // We can't recover from these exceptions, so our only option is to close the producer and exit.
			 producer.close();
		} catch (KafkaException e) {
			 // For all other exceptions, just abort the transaction and try again.
			 producer.abortTransaction();
		}
		producer.close();
    }
}