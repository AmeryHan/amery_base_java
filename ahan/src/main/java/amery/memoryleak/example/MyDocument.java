package amery.memoryleak.example;

public class MyDocument {
	private String myText;
	private String docName;
	private int [] myOtherData;
	
	public MyDocument(String myText, String docName) {
		this.myText = myText;
		this.docName = docName;
		this.myOtherData = new int [1024*1024];
	}
	
	public String getMyText() {
		return myText;
	}

	public String getDocName() {
		return docName;
	}
	
	
}
