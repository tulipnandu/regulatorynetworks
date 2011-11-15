use LWP::Simple;
$|=1;
open( OUT,"+>./remain_map.txt")|| die "can't open";

my $progress = 0;
my %tax;
open( IN,"< ./remain.txt")|| die "can't open";
while($line = <IN>)
{
    chomp($line);
    print OUT $line."\t".ID2OtherAliases(SearchTerm($line))."\n";
    
    $progress++;
    if($progress%3 == 0)
    {
        sleep(1);
    }
    print $progress."/403\n";
}

sub ID2OtherAliases
{
    my $id = $_[0];
    my $url='http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esummary.fcgi?'.
            'db=gene&id='.$id;
            
    my $result = get($url);
    
    my $aliases;
    my @lines = split("\n",$result);
    for(my $i=0;$i<=$#lines;$i++)
    {
        if(index($lines[$i],'OtherAliases') != -1)
        {
            $aliases = substr($lines[$i],index($lines[$i],'>')+1);
            $aliases = substr($aliases,0,index($aliases,'<'));
            last;
        }
    }
    
    return $aliases;
}

sub SearchTerm
{
    my $term = $_[0];
    my $url='http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?'.
            'db=gene&term='.$term;
    
    my $result = get($url);

    my $firstID;
    my @lines = split("\n",$result);
    for(my $i=0;$i<=$#lines;$i++)
    {
        if(index($lines[$i],'<Id>') != -1)
        {
            $firstID = substr($lines[$i],index($lines[$i],'>')+1);
            $firstID = substr($firstID,0,index($firstID,'<'));
            last;
        }
    }
    
    return $firstID;
}
