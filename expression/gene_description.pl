use LWP::Simple;
$|=1;
open( OUT,"+>./0.3+description.csv")|| die "can't open";

my $progress = 0;

open( IN,"< ./0.3.csv")|| die "can't open";
while($line = <IN>)
{
    chomp($line);
    my @toks = split(",",$line);
    print OUT $line.",".ID2description(SearchTerm($toks[0]))."\n";
    
    $progress++;
    if($progress%5 == 0)
    {
        sleep(1);
    }
    print $progress."/642\n";
}

sub ID2description
{
    my $id = $_[0];
    my $url='http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esummary.fcgi?'.
            'db=gene&id='.$id;
            
    my $result = get($url);
    
    my $description;
    my @lines = split("\n",$result);
    for(my $i=0;$i<=$#lines;$i++)
    {
        if(index($lines[$i],'Description') != -1)
        {
            $description = substr($lines[$i],index($lines[$i],'>')+1);
            $description = substr($description,0,index($description,'<'));
            last;
        }
    }
    
    return $description;
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
