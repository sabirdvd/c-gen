#! /bin/bash

# some var
server_list=server.ini
param=
host=
item_name=
user=
# help
if [ "$1" = "help" ] ; then
	echo 'synchronous cm properties from bg to other'
	exit 0
fi
echo "param is $1"
while read LINE 
do
param=$(echo $LINE | awk '{print $1}')			
war_name=$(echo $LINE | awk '{print $2}')
host=$(echo $LINE | awk '{print $4}')
item_name=$(echo $LINE | awk '{print $3}')
user=$(echo $LINE | awk '{print $5}')
if [ "bg" = $param ] || [ "app" = $param ]; then
	continue
fi

echo "-- now $param --" 
ssh -T $user@$host<<EOF
bar=$1
if [ -z "$1" ]; then
 	bar="cdn.properties defence.properties ehcache.xml mail_template.xml memcached.properties messages.properties online.properties RTMMail.properties sendMessages.properties SMS.properties"
fi
for boo in \$bar
do
	cp /opt/redhat/jboss-eap-5.1/jboss-as/server/background/deploy/GomeBg.war/WEB-INF/classes/\${boo} /opt/redhat/jboss-eap-5.1/jboss-as/server/${item_name}/deploy/Gome${war_name}.war/WEB-INF/classes
done
EOF
echo "-- done! --"
done < server.ini

echo "finished!"
exit 0
