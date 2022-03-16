public class S3Sdk {

	public void Foo() {
		TransferManager.copy("SourceBucket1", "Key1", "SinkBucket1", "Key1"); // Unknown Action
		TransferManager.download("Bucket2", "Key1");
		TransferManager.downloadDirectory("Bucket3", "Key1");
		TransferManager.upload("Bucket4", "Key1");
		TransferManager.uploadDirectory("Bucket5", "Key1");
		TransferManager.uploadFileList("Bucket6", "Key1");
		
		S3BucketEntity b1 = new S3BucketEntity("Bucket7", "Key1"); // Unknown Action
		SetBucketNotificationConfigurationRequest b2 = new SetBucketNotificationConfigurationRequest("Bucket8", "Key8"); // Unknown Action
		
		AmazonS3.completeMultipartUpload("Bucket9");
		AmazonS3EncryptionV2.copyObject("SourceBucket10", "Key10", "SinkBucket10", "Key10"); // Unknown Action
		AmazonS3Encryption.copyPart("SourceBucket11", "Key11", "SinkBucket11", "Key11"); // Unknown Action
		AbstractAmazonS3.deleteObject("Bucket12");
		AbstractAmazonS3EncryptionV2.deleteObjects("Bucket13");
		AmazonS3Client.deleteObjectTagging("Bucket14");
		AmazonS3EncryptionClient.download("Bucket15");
		AmazonS3EncryptionClientV2.getObject("Bucket16");
		S3Client.getObjectMetadata("Bucket17");
		AmazonS3.putObject("Bucket18");
		AmazonS3.selectObjectContent("Bucket19");
		AmazonS3.setBucketAcl("Bucket20"); // Unknown Action
		AmazonS3.upload("Bucket21");
		AmazonS3.uploadPart("Bucket22");
		AmazonS3EncryptionV2.uploadObject("Bucket23");
		AmazonS3.deleteVersion("Bucket124");
	}
}