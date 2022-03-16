public class Foo {
	public void registerListenerContainer() {

		String destination = "Queue 455";
		MessageListenerContainer container = createListenerContainer();
		container.setDestination(destination);
	}
}