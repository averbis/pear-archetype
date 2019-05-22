package com.example.nlp;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

public class ContractAnnotator extends JCasAnnotator_ImplBase {
	
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		
		super.initialize(context);
		
		// add your annotator initialization logic here
	}
	
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		
		// add your annotator processing logic here
	}
}