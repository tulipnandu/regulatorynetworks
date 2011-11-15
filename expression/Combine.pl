$|=1;
open( OUT1,"+> ./0.3.csv")|| die "can't open $tmpPred";
open( OUT2,"+> ./0.5.csv")|| die "can't open $tmpPred";
open( OUT3,"+> ./all.csv")|| die "can't open $tmpPred";

my %id2status;
open( IN,"< ./Final_1_UpDown_s.csv")|| die "can't open $inputFile";
while(my $line = <IN>)
{
    chomp($line);
    my @toks = split(",",$line);
    $id2status{$toks[0]} = $toks[1].",".$toks[2].",".$toks[3].",".$toks[4];
}

my %name2id;
open( IN,"< ./tn_cyano_loci.txt")|| die "can't open $inputFile";
while(my $line = <IN>)
{
    chomp($line);
    my @toks = split("\t",$line);
    $name2id{$toks[0]} = $toks[1];
}
open( IN,"< ./remain_map.txt")|| die "can't open $inputFile";
while(my $line = <IN>)
{
    chomp($line);
    my @toks = split("\t",$line);
    $name2id{$toks[0]} = $toks[1];
}

open( IN,"< ./0.3_data.csv")|| die "can't open $inputFile";
while(my $line = <IN>)
{
    chomp($line);
    my @toks = split(',',$line);
    print OUT1 $line.",".$id2status{$name2id{$toks[0]}}."\n";
}

open( IN,"< ./0.5_data.csv")|| die "can't open $inputFile";
while(my $line = <IN>)
{
    chomp($line);
    my @toks = split(",",$line);
    print OUT2 $line.",".$id2status{$name2id{$toks[0]}}."\n";
}

open( IN,"< ./all_data.csv")|| die "can't open $inputFile";
while(my $line = <IN>)
{
    chomp($line);
    my @toks = split(",",$line);
    print OUT3 $line.",".$id2status{$name2id{$toks[0]}}."\n";
}
