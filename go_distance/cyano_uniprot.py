'''
Created on Jul 29, 2011

@author: Yogesh
'''

from Bio import SwissProt

file = open('C:/Users/Yogesh/Desktop/uniprot-cyanothece+sp+51142(1).txt')
#pfamfile = open('C:/tn_cyano_pfam.txt', 'w')
#gofile = open('C:/tn_cyano_go.txt', 'w')
locifile = open('C:/tn_cyano_loci.txt', 'w')

for record in SwissProt.parse(file):
    accessions = record.accessions
    gene_name = record.gene_name
    genes = gene_name.split(';')
    
    if len(genes) is 2:
        #gene = genes[0].replace("Name=", "")
        loci = genes[0].split(',')
        for i in range(len(loci)):
            data0 = accessions[0] + '\t' + loci[i] + '\n'
            locifile.write(data0)
            print data0
    elif len(genes) is 1:
        loci = genes[0].split(',')
        for i in range(len(loci)):
            data0 = accessions[0] + '\t' + loci[i] + '\n'
            locifile.write(data0)
            print data0
        
    '''cross_ref = record.cross_references
    for cr in cross_ref:
        if cr[0].startswith('Pfam'):
            data1 = accessions[0] + '\t' + cr[1] + '\t' + cr[2] + '\n'
            pfamfile.write(data1)
            print data1
    for cr in cross_ref:
        if cr[0].startswith('GO'):
            data2 = accessions[0] + '\t' + cr[1] + '\t' + cr[2] + '\n'
            gofile.write(data2)
            print data2 '''

file.close()
