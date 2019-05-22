package ${package};

import java.io.File;
import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.SimpleNamedResourceManager;
import org.apache.uima.jcas.JCas;
import org.apache.uima.pear.tools.PackageBrowser;
import org.apache.uima.pear.tools.PackageInstaller;
import org.apache.uima.resource.PearSpecifier;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

/*
 * This integration test ensures that the annotator PEAR package fulfills
 * the minimal requirements to be installed by the UIMA PackageInstaller 
 * and to process text
 */
public class PearPackageIT {

	/*
	 * Make sure that the annotator PEAR package 
	 * can be installed by the UIMA PackageInstaller
	 */
	@Test
	public void testInstallPearPackage() {
		
		this.installPackage();			
	}
	
	/*
	 * Make sure that the annotator is able to 
	 * process text without exceptions
	 */
	@Test
	public void testProcessText() throws IOException, UIMAException {
		
		PackageBrowser packageBrowser = this.installPackage();		
		XMLInputSource xmlInputSource = new XMLInputSource(packageBrowser.getComponentPearDescPath());
		PearSpecifier pearSpecifier = UIMAFramework.getXMLParser().parsePearSpecifier(xmlInputSource);
		AnalysisEngine analysisEngine = UIMAFramework.produceAnalysisEngine(pearSpecifier, new SimpleNamedResourceManager(), null);
		
		JCas jCas = JCasFactory.createJCas();
		jCas.setDocumentText("Test document text");
		
		analysisEngine.process(jCas);
	}

	private PackageBrowser installPackage() {
		
		File installDir = new File("target/generated-test-sources");
		File pearPackage = new File("target/${artifactId}-${version}.pear");
		
		return PackageInstaller.installPackage(installDir, pearPackage, true, false);
	}
}
