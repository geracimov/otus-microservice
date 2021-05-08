


Y=25

DIV=$((Y+1))


while true ; do 

	R=$(($RANDOM%$DIV))
	ab -n3 -c1  http://arch.homework/otusapp/geracimov/user/$R
	sleep 0.2
done 
