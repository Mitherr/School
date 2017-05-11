public class Application {
	
	

	public static void main(String[] args) {		
		FileReader fr = new FileReader();
		CorpusRepresentation learningData = fr.readTextCorpus("czech_text_document_corpus_v10", 1, 10900);
		CorpusRepresentation testingData = fr.readTextCorpus("czech_text_document_corpus_v10", 10901, 11001); 
		TextFeatures features = new PositionAndFrequencyFeature();
		
		NearestNeighbour classifier = new NearestNeighbour(learningData,features);
		classifier.classifyData(testingData);
		
		System.out.println(classifier.accuracy);
		
	}

}
