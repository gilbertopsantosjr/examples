
Exec { path =>  [ "/bin/", "/sbin/" , "/usr/bin/", "/usr/sbin/" ] }

class base {

  ## Update apt-get ##
  exec { 'apt-get update':
    command => '/usr/bin/apt-get update'
  }
}

class ci {
    exec{ "create-ci-env":
        command => "/vagrant/manifests/docker-ci-tool/ ./setup.sh",
        unless => "sudo docker run hello-world",
        path => "/usr/bin",
        require => Service["docker"]
    }

    service{ "docker":
    	ensure => running,
    	enable => true,
    	hasstatus => true,
    	hasrestart => true,
    	ensure => present,
    }
}

include base
include ci
