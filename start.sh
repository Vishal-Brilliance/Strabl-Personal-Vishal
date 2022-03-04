mkdir -p logs  # Create logs directory if it doesn't exist already

killall java > /dev/null 2>&1
echo "Stopping all running services ... Waiting for 10 seconds"
sleep 10

nohup java -Dspring.profiles.active=PROD -jar "./discovery-service/target/discovery.jar" > ./logs/discovery.log 2>&1 &
echo "Started Discovery Service ... Waiting for 12 seconds"
sleep 12

nohup java -Dspring.profiles.active=PROD -jar "./user-service/target/user.jar" > ./logs/user.log 2>&1 &
sleep 1
echo "Started User Service ..."

nohup java -Dspring.profiles.active=PROD -jar "./notification-service/target/notification.jar" > ./logs/notification.log 2>&1 &
sleep 1
echo "Started Notification Service ..."

nohup java -Dspring.profiles.active=PROD -jar "./product-service/target/product.jar" > ./logs/product.log 2>&1 &
sleep 1
echo "Started Product Service ..."

echo "Waiting for 25 seconds ..."
sleep 25

nohup java -Dspring.profiles.active=PROD -jar "./gateway-service/target/gateway.jar" > ./logs/gateway.log 2>&1 &
echo "Started Gateway Service ... ALL DONE!"
