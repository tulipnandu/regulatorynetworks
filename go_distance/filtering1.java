package parsing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 *
 * @author PRASAD
 */
public class filtering1
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("/Users/tulip_nandu/Desktop/BiomapResult_induced.txt"));
        
        String s1 = null;

        s1=br.readLine();

        String[] s2 = new String[9];
        String[] s4 = new String[9];
        
        s2=s1.split("\t");
       
        List<String> DD1 = new ArrayList<String>();
        List<String> DD2 = new ArrayList<String>();
        List<String> DD3 = new ArrayList<String>();
        List<String> DD4 = new ArrayList<String>();
        List<String> DU2 = new ArrayList<String>();
        List<String> NU3 = new ArrayList<String>();
        List<String> ND2 = new ArrayList<String>();
        List<String> DU4 = new ArrayList<String>();
        List<String> ND3 = new ArrayList<String>();
        List<String> NU2 = new ArrayList<String>();
        List<String> DU3 = new ArrayList<String>();
        
        String s3 = null;
        
        int count =1;
        
        while((s3=br.readLine())!=null)
        {
        	
        	
        	s4 = s3.split("\t");
        	       	
        	System.out.println("Line: " + count++);
        	
        	DD1.add(s4[0]);
            DU2.add(s4[1]);
            DD2.add(s4[2]);
            NU3.add(s4[3]);
            ND2.add(s4[4]);
            DU4.add(s4[5]);
            ND3.add(s4[6]);
            NU2.add(s4[7]);
            DD4.add(s4[8]);
            DU3.add(s4[9]);
            DD3.add(s4[10]);
            
        }
        
        
        
        BufferedReader br1 = new BufferedReader(new FileReader("/Users/tulip_nandu/Desktop/combined_uniprot-1.txt"));
        
        String s6 = null;
        
        List<String> toComapre = new ArrayList<String>();
        
        while(( s6 =br1.readLine())!=null)
        {
        	  toComapre.add(s6);
        }
        ListIterator<String> masterIterator = DD3.listIterator();
        ListIterator<String> compareIterator;
        
        String masterWords = null;
        String compareWords = null;
        
       List<String> newDD1 = new ArrayList<String>();
       Set<String> retainedMasterSet = new HashSet<String>();
        
        while(masterIterator.hasNext())
        {
        	masterWords = masterIterator.next();
        	
        	compareIterator = toComapre.listIterator();
        	
        	  while(compareIterator.hasNext())
        	  {
        		  compareWords = compareIterator.next();
        			
        		  if( masterWords.length()>0 && compareWords.indexOf(masterWords.trim())!=-1)
        			{
        			  retainedMasterSet.add(masterWords);
        			  newDD1.add(masterWords);
        			}
        	  }
        }
        
        
        BufferedWriter obj3 = new BufferedWriter(new FileWriter("/Users/tulip_nandu/Desktop/sampleoutput_dd3.txt"));
        
      /*  for(int i=0;i<newDD1.size();i++)
        {
        	obj3.write(newDD1.get(i));
        	obj3.newLine();
        }*/
        
        Iterator <String> setIterator =retainedMasterSet.iterator();
        
        while(setIterator.hasNext())
        {
        	obj3.write(setIterator.next());
        	obj3.newLine();
        }
        
        obj3.newLine();
        obj3.close();
        
    }
    
}
