/*
 * Copyright 2021 Averbis GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.nlp;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
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

		Assert.assertEquals("SomeType annotations", 1,
				cas.select(SomeType.class).count());
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
