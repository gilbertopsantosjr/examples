/**
 * 
 */
(function(){
	
	var app = angular.module('mailBoxApp', ['restangular', 'ngMaterial', 'ngMessages']);
	
	var MainController = function ($scope, $http, $interval, $log, Restangular)
	{
		
		$scope.messages = [];
		$scope.backgroundColor = "f2f2f2";
		$scope.messageProvider = {
				nNumber:"demo",
				password: "123456", 
				connected: false
		};
		
		var doWork = null; 
		
		var start = function (){
			doWork = $interval(callInbox, 20000);
		};
		
		var stop = function(){
			$log.info("not working !");
			$scope.backgroundColor = "f2f2f2";
			$interval.cancel(doWork);
		}
		
		var callInbox = function(){
			$scope.backgroundColor = "00ff00";
			Restangular.all('show/in/box/' + $scope.messageProvider.nNumber)
				.getList().then(
						function(collection){
							$log.info("working !");
							$scope.messages = collection;
				});	
		}
		
		$scope.decideIfWillWork = function() {
			// verify if pool has created
			if(!$scope.messageProvider.connected){
				// if not create a pool
				Restangular.all('connect').post($scope.messageProvider)
					.then(
						function(newResource){
							$scope.messageProvider = newResource;
							// call inbox for each 2 minutes
							 start();
						});
			} else {
				// disconnect a pool
				Restangular.all('disconnect').post($scope.messageProvider)
					.then(
						function(newResource){
							$scope.messageProvider = newResource;
							stop();
					});
			}
		}
		
		$scope.addAlert = function(message) {
			$scope.alerts.push({ type: 'danger', msg: message });
		};

		$scope.closeAlert = function(index) {
		    $scope.alerts.splice(index, 1);
		};
		
		Restangular.setErrorInterceptor(
				function(response, deferred, responseHandler) {
					if(response.status != 200){
						if(response.data != null)
							$scope.addAlert(response.data.message);
					}
					return false; 
				}
		);
	}
	
	app.controller("MainController", ["$scope", "$http", "$interval", "$log", "Restangular", MainController]);
	
	app.config(["RestangularProvider", function(RestangularProvider){
		RestangularProvider.setBaseUrl("http://localhost:8080/mail");
		RestangularProvider.setDefaultHeaders({'Content-Type': 'application/json; charset=utf-8'});
	}]);
	
}());

