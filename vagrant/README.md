# CI Tools Demo

After install The Vagrant and VirtualBox into your machine, clone this with 

git clone https://github.com/gilbertopsantosjr/vagrant

then go through your directory  and run this command 

vagrant up  

waiting for few minutes then go through your browser and type for 

192.168.50.10 

you may be able to see this CI environment such as below

if you have any problems , go through your machine using ssh command 

ssh vagrant@192.168.50.10 
user: vagrant
password: vagrant 

then type to: 

docker stop `docker ps -qa`

docker start `docker ps -qa`

and see yourself all services starting to work then go back to the browser 

192.168.50.10


have fun. 
