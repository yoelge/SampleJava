public class EnableNotificationOnABucket {

    public static void send() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.useNio();

		SenderOptions senderOptions =  new SenderOptions()
			.connectionFactory(connectionFactory)                         
			.resourceManagementScheduler(Schedulers.boundedElastic());
		
		Sender sender = RabbitFlux.createSender(senderOptions);
		
		Flux<OutboundMessage> outboundFlux  =
		Flux.range(1, 10)
			.map(i -> new OutboundMessage(
				"",
				"routing.key", ("Message " + i).getBytes()));
				
		sender.send(outboundFlux)                         
			.doOnError(e -> log.error("Send failed", e))  
			.subscribe(); 
    }
	
	public static void reactive () {
        ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.useNio();

		ReceiverOptions receiverOptions =  new ReceiverOptions()
			.connectionFactory(connectionFactory)                          
			.connectionSubscriptionScheduler(Schedulers.boundedElastic());
			
		SenderOptions senderOptions =  new SenderOptions()
			.connectionFactory(connectionFactory)
			.connectionSupplier(cf -> cf.newConnection(                                  
				new Address[] {new Address("192.168.0.1"), new Address("192.168.0.2")},
				"reactive-sender"))
			.resourceManagementScheduler(Schedulers.boundedElastic());
			
		Receiver receiver = RabbitFlux.createReceiver();
		BiConsumer<Receiver.AcknowledgmentContext, Exception> exceptionHandler =
			new ExceptionHandlers.RetryAcknowledgmentExceptionHandler(                  
				Duration.ofSeconds(20), Duration.ofMillis(500),
				ExceptionHandlers.CONNECTION_RECOVERY_PREDICATE
		);
		receiver.consumeManualAck("queue",
					new ConsumeOptions().exceptionHandler(exceptionHandler))
				.subscribe(msg -> {
					// ...                                                              
					msg.ack();                                                          
				});
    }
}