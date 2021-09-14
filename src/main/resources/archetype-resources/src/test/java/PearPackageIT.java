package ${package};

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.SimpleNamedResourceManager;
import org.apache.uima.jcas.JCas;
import org.apache.uima.pear.tools.PackageBrowser;
import org.apache.uima.pear.tools.PackageInstaller;
import org.apache.uima.resource.PearSpecifier;
import org.apache.uima.util.XMLInputSource;
import org.junit.Assert;
import org.junit.Test;

/*
 * This integration test ensures that the annotator PEAR package fulfills
 * the minimal requirements to be installed by the UIMA PackageInstaller 
 * and to process text
 */
public class PearPackageIT {

	@Test
	public void testProcessText() throws Exception {
		
		PackageBrowser packageBrowser = this.installPackage();
		XMLInputSource xmlInputSource = new XMLInputSource(packageBrowser.getComponentPearDescPath());
		PearSpecifier pearSpecifier = UIMAFramework.getXMLParser().parsePearSpecifier(xmlInputSource);
		AnalysisEngine analysisEngine = UIMAFramework.produceAnalysisEngine(pearSpecifier, new SimpleNamedResourceManager(), null);
		
		JCas jcas = JCasFactory.createJCas();
		CAS cas = jcas.getCas();
		jcas.setDocumentText("Test document text");

		analysisEngine.process(jcas);

		Assert.assertEquals("SomeType annotations", 3,
				cas.select("${package}.SomeType").count());
	}

	private PackageBrowser installPackage() {

		File installDir = new File("target/generated-test-sources");
		
		Collection<File> files = FileUtils.listFiles(new File("target/"), new String[] { "pear" },
				false);

		Assert.assertEquals(1, files.size());
		File pearPackage = files.iterator().next();

		return PackageInstaller.installPackage(installDir, pearPackage, true, false);
	}
}
