/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import uk.ac.ebi.kraken.interfaces.uniprot.Gene;
import uk.ac.ebi.kraken.interfaces.uniprot.UniProtEntry;
import uk.ac.ebi.kraken.interfaces.uniprot.dbx.go.Go;
import uk.ac.ebi.kraken.interfaces.uniprot.evidences.Evidence;
import uk.ac.ebi.kraken.interfaces.uniprot.genename.OrderedLocusName;
import uk.ac.ebi.kraken.uuw.services.remoting.EntryIterator;
import uk.ac.ebi.kraken.uuw.services.remoting.Query;
import uk.ac.ebi.kraken.uuw.services.remoting.RemoteDataAccessException;
import uk.ac.ebi.kraken.uuw.services.remoting.UniProtJAPI;
import uk.ac.ebi.kraken.uuw.services.remoting.UniProtQueryBuilder;
import uk.ac.ebi.kraken.uuw.services.remoting.UniProtQueryService;

/**
 * GetAllGenesFromUniProt.java
 *
 * @author Yogesh
 * @date Mar 7, 2011
 */
public class GetAllGenesFromUniProt {

	static File outFile = new File("all_human_genes_uniprot.txt");

	/*
	 * public static void main(String[] args) throws RemoteDataAccessException,
	 * IOException { // TODO Auto-generated method stub UniProtQueryService
	 * uniProtQueryService = UniProtJAPI.factory.getUniProtQueryService();
	 * //Query query =
	 * UniProtQueryBuilder.buildProteinNameQuery("Homo sapiens"); Query query =
	 * UniProtQueryBuilder.buildOrganismQuery("Homo sapiens");
	 * EntryIterator<UniProtEntry> entryIterator =
	 * uniProtQueryService.getEntryIterator(query); for (UniProtEntry
	 * uniProtEntry : entryIterator) { String orgn =
	 * uniProtEntry.getOrganism().toString(); String accession =
	 * uniProtEntry.getPrimaryUniProtAccession().getValue(); String entry =
	 * uniProtEntry.getUniProtId().getValue(); List<Gene> genes =
	 * uniProtEntry.getGenes(); //Iterator itr = genes.iterator(); //while
	 * (itr.hasNext()) { for (Gene g : genes) { String gene =
	 * g.getGeneName().getValue(); String data = accession + "\t" + entry + "\t"
	 * + gene; System.out.println(data); write2File(data); } } }
	 */

	public static void main(String[] args) throws RemoteDataAccessException, IOException {
		// TODO Auto-generated method stub

		UniProtQueryService uniProtQueryService = UniProtJAPI.factory.getUniProtQueryService();

		//Query query = UniProtQueryBuilder.buildProteinNameQuery("Homo sapiens");
		Query query = UniProtQueryBuilder.buildOrganismQuery("synechococcus WH 8102");

		EntryIterator<UniProtEntry> entryIterator = uniProtQueryService.getEntryIterator(query);
		System.out.println(entryIterator.getResultSize());

		for (UniProtEntry uniProtEntry : entryIterator) {
			String accession = uniProtEntry.getPrimaryUniProtAccession().getValue();
			String orgn = uniProtEntry.getOrganism().toString();

			List<Gene> genes = uniProtEntry.getGenes();
			/*for (Gene g : genes) {
				String gene = g.getGeneName().getValue();
				//List<OrderedLocusName> locus = g.getOrderedLocusNames();
				//for (OrderedLocusName loci : locus) {
				//String l = loci.getValue();
				String data = accession + "\t" + gene + "\t";//+ l;
				//}

				//write2File(data); 
			}

			/*
			 * List<Go> gos = uniProtEntry.getGoTerms(); for (Go go : gos) {
			 * String type = go.getOntologyType().getValue(); String goid =
			 * go.getGoId().getValue(); String name = go.getGoTerm().getValue();
			 * String data = accession + "\t" + type + "\t" + goid + "\t" +
			 * name; System.out.println(data); //write2File(data); }
			 */

			List<Evidence> eIds = uniProtEntry.getEvidences();
			for (Evidence eId : eIds) {
				System.out.println(eId.getType().getValue());
			}

		}
	}

	public static void write2File(String data) throws IOException {

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile, true)));

		bw.write(data);

		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
