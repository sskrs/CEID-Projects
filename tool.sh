#!/bin/bash

#to $# metraei tn arithmo twn arguments p dinontai apo t xrhsth 
if [ $# -eq 0 ]; then
    echo "Arithmos mhtrwou"
fi

#dhlwsh olwn twn arguments pou mporei n dwthoun
Option=$1
file=$2
idi=$3
ididi=$4
datA=$5
datB=$6

# A) elegxei an o arithmos twn arguments einai isos me 2 an einai mpainei sthn if
if [ $# -eq 2 ] ; then
#elegxos prwtou argument
#h case elegxei poio einai t prwto argument p dinei o xrhsths
case $Option in
-f|-F)
#an t arxeio einai mhdenikoy megethous dn kanei kanei exit alliws emfanizei olo t arxeio xwris thn grammh 
#pou ksekina me #
[ -z $file ] && { exit 1; } ||grep -v "#" $file | cat 
;;
esac
exit 1
elif [ $# -eq 4 ] ; then
case $idi in
-id|-ID)
#cat to arxeio, to taksinomei basei ths prwths sthlhs
#sthn awk o delimiter einai | kai ektupwnei 1h,3h,2h,5h sthlh 
#psaxnei to t arxeio g n brei to 4o argument kai afairei thn prwth grammh p periexei t to "#"
#telos tupwnei 2h 3h 4h sthlh kata seira kai afairei me thn tr -s " " t peritta kena
cat $file | sort -nk1 |awk -F"|" '{print $1,$3,$2,$5}'|grep -w "^$ididi"| sed -n '1p;1q'|awk '{print $2, $3, $4}'|tr -s " "
;;
-f|-F)
#idia logikh me to -id parapanw
[ -z $ididi ] && { exit 1; } ||grep -v "#" $ididi | sort -nk1 |awk -F"|" '{print $1,$3,$2,$5}'|grep -w "^$file"| sed -n '1p;1q'|awk '{print $2, $3, $4}'|tr -s " "
;;
esac
fi


#c kai d erwthma
if [ $# -eq 3 ] ; then
if [ $file == "-f" ] ; then
if [ -f $idi ] ; then
case $Option in
--firstnames)
#cat to arxeio, h grep deixnei oles tis grammes t arxeioy ektos apo auth p arxizei m #, h awk ektupwnei trith sthlh
#h sort -u sortarei t arxeio afairontas t dipla onomata
cat $idi |grep -v "^#" | awk -F"|" '{print $3}' | sort -u 
;;
--lastnames)
cat $idi |grep -v "^#"| awk -F"|" '{print $2}'  | sort -u 
;;
--browsers)
cat $idi |grep -v "^#" |awk -F"|" '{print $8}' | sort | uniq -c |  tr -dc '[:print:]\n' | awk '{print $2,$3,$1}'| tr -s " "
;;
esac
fi
fi
fi
#elegxos auth t fora an t prwto argument einai t -f 
if [ $Option == "-f" ] ; then
#elegxos an yparxei t arxeio p eisagei o xrhsths sto deytero argument
if [ -f $file  ]; then
  if [ $idi == "--firstnames" ]; then
    cat $file |grep -v "^#"| awk -F"|" '{print $3}' | sort -u
  elif [ $idi == "--lastnames" ] ; then
    cat $file |grep -v "^#" |awk -F"|" '{print $2}' | sort -u
  elif [ $idi == "--browsers" ]; then
#anoigei t arxeio m thn cat, deixnei olo t arxeio ektos apo t grammh p arxizei m #
#ektupwnei thn teleutaia sthlh thn ta3inomei, afairei t peritta dipla, ektupwnei thn 2h,3h pou einai kenh kai 1h sthlh t tropopoihmenou arxeioy
#afairei t peritta kena, h $3 xreiazetai sthn awk g n ektupwsei oloklhro to onoma internet explorer kathos t bash katalabainei ts 2 le3eis sn 
#duo sthles
    cat $file |grep -v "^#"|awk -F"|" '{print $8}' | sort | uniq -c |  tr -dc '[:print:]\n' | awk '{print $2,$3,$1}'| tr -s " "
fi
fi 
fi


#ERWTHMA e
#elegxos g tis periptwseis p einai m 4 arguments
if [ $# -eq 4 ] ; then
case $Option in
--born-since)
if [ $idi = "-f" ] ; then
#deixnei t arxeio arxika oloklhro kai meta thn grep, xwris thn grammh p 3ekina m #
#mesa sthn awk dhlwnetai h metablhth gia n mpei sth sunthhkh ths if kai an isxuei h sunthhkh 
#ektupwnetai h antistoixh grammh
cat $ididi | grep -v "^#" | awk -F"|" -v dataA=$file '{ if ($5 == dataA || $5 > dataA) print $0 ;}'
fi 
;;
--born-until)
if [ $idi = "-f" ] ; then 
cat $ididi | grep -v "^#" | awk -F"|" -v dataB=$file '{ if ($5 == dataB || $5 < dataB) print $0 ;}'
fi 
;;
esac
#Periptwsh ./tool.sh -f <F> --born-since <dateA>
if [ $Option = "-f" ] ; then
if [ -f $file ] ; then 
if [ $idi = "--born-since" ] ; then
#deixnei t arxeio, deixnei t arxeio xwris t grammh p arxizei me #, dhlwnetai h metablhth dataA (date p dinei o xrhsths)  mesa sthn awk kai 
#an einai mikroterh ;h isi me to stoixeio ths sthlhs 5, ektypwnei olh t grammh
cat $file | grep -v "^#" | awk -F"|" -v dataA=$ididi '{ if ($5 == dataA || $5 > dataA) print $0 ;}'
#periptwsh ./tool.sh -f <F> --born-until <dateB>
elif [ $idi = "--born-until" ] ; then
#idio me t parapanw apla ginetai sugkrish an h deuterh hmeromhnia einai megaluterh h isi m t stoixeio ths pempths sthlhs
cat $file | grep -v "^#" | awk -F"|" -v dataB=$ididi '{ if ($5 == dataB || $5 < dataB) print $0 ;}'
fi
fi
fi
fi
#PERIPTWSEIS ME 6 ARGUMENTS SUNOLO!
if [ $# -eq 6 ] ; then 
#periptwsh --born-since <dateA> --born-until <dateB> -f <F>
case $Option in
--born-since)
if [ $idi = "--born-until" ] ; then
if [ $datA = "-f" ] ; then 
if [ -f $datB ] ; then
#deixnei to arxeio, deixnei t arxeio xwris t grammh p 3ekina me #, dhlwnontai 2 metablhtes mesa sthn awk kai an isxuei h sunthhkh sthn if
#ektupwnetai h antistoixh grammh
cat $datB | grep -v "^#" | awk -F"|" -v dataA=$file -v dataB=$ididi '{ if ( $5 >= dataA && $5 <= dataB ) print $0 ;}'
fi
fi
fi
#periptwsh ./tool.sh --born-since <dateA> -f <F> --born-until <dateB> 
if [ $idi = "-f" ] ; then
if [ -f $ididi ] ; then
if [ $datA = "--born-until" ] ; then
cat $ididi | grep -v "^#" | awk -F"|" -v dataA=$file -v dataB=$datB '{ if ( $5 >= dataA && $5 <= dataB ) print $0 ;}'
fi
fi
fi
;;
#periptwsh ./tool.sh --born-until <dateB> --born-since <dateA> -f <F>
--born-until)
if [ $idi = "--born-since" ] ; then
if [ $datA = "-f" ] ; then
if [ -f $datB ] ; then 
cat $datB | grep -v "^#" | awk -F"|" -v dataA=$ididi -v dataB=$file '{ if ( $5 >= dataA && $5 <= dataB ) print $0 ;}'
fi
fi
fi
#periptwsh ./tool.sh --born-until <dateB> -f <F> --born-since <dateA>
if [ $idi = "-f" ] ; then
if [ -f $ididi ] ; then
if [ $datA = "--born-since" ] ; then
cat $ididi | grep -v "^#" | awk -F"|" -v dataA=$datB -v dataB=$file '{ if ( $5 >= dataA && $5 <= dataB ) print $0 ;}'
fi
fi
fi
;;
#periptwsh ./tool.sh -f <F> --born-since <dateA> --born-until <dateB>
-f|-F)
if [ -f $file ] ; then
if [ $idi = "--born-since" ] ; then
if [ $datA = "--born-until" ] ; then
cat $file | grep -v "^#" | awk -F"|" -v dataA=$ididi -v dataB=$datB '{ if ( $5 >= dataA && $5 <= dataB ) print $0 ;}'
fi
fi
fi
#periptwsh ./tool.sh -f <F> --born-until <dateB> --born-since <dateA>
if [ -f $file ] ; then
if [ $idi = "--born-until" ] ; then
if [ $datA = "--born-since" ] ; then 
cat $file | grep -v "^#" | awk -F"|" -v dataA=$datB -v dataB=$ididi '{ if ( $5 >= dataA && $5 <= dataB ) print $0 ;}'
fi
fi
fi
;;
esac
fi  

