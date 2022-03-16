public class SimpleResourceLoadingBean {

	@Autowired
	private ResourceLoader resourceLoader;

	public void resourceLoadingMethod() throws IOException {
		Resource resource = this.resourceLoader.getResource("s3://myBucket/rootFile.log");
		Resource secondResource = this.resourceLoader.getResource("s3://myBucket/rootFolder/subFile");

		InputStream inputStream = resource.getInputStream();
		//read file
	}
	
	public void writeResource() throws IOException {
        Resource resource = this.resourceLoader.getResource("s3://myBucket/rootFile.log");
        WritableResource writableResource = (WritableResource) resource;
        try (OutputStream outputStream = writableResource.getOutputStream()) {
            outputStream.write("test".getBytes());
        }
    }
	
	public void otherSamples() throws IOException {
		String bucketName = "myBucket2";
		Resource resource = this.resourceLoader.getResource("s3://" + bucketName + "/rootFile.log");
		InputStream inputStream = resource.getInputStream();
		OutputStream outputStream = resource.getOutputStream()
		
		String fileName = "myFile";
		Resource resource2 = this.resourceLoader.getResource("s3://" + bucketName + "/" + fileName);
		
		String s3 = "s3://";
		Resource resource3 = this.resourceLoader.getResource(s3 + bucketName + "/" + fileName);
	}
}