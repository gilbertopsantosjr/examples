

var feedApi = angular.module('FeedApp', ["restangular", 'ui.bootstrap', 'ngAnimate']);

feedApi.config(["RestangularProvider",function(RestangularProvider){
	RestangularProvider.setBaseUrl("http://localhost:8080/feed");
	RestangularProvider.setDefaultHeaders({'Content-Type': 'application/json; charset=utf-8'});
}]);

feedApi.controller("feedManagerController",["Restangular","$scope", "$timeout", function(Restangular, $scope, $timeout){
	
	$scope.alerts = [];
	$scope.providers = [];
	$scope.select = null; 
	
	$scope.feedProvider = {
			nNumber:"n0260983",
			url: "",
	        nickName: ""
	 };
	
	var init = function() {
		Restangular.all('show/all/' + $scope.feedProvider.nNumber).getList().then(function(collection){
			$scope.providers = collection;
		});
	}
	
	var clean = function(){
		$scope.feedProvider = {
				nNumber:"n0260983",
				url: "",
		        nickName: ""
		}
	}
	
	init();

	$scope.selectItem = function (item){
		$scope.select = item;
	}
	
	$scope.unselect = function (){
		delete $scope.select;
	}
	
	/* call get rest service*/
	$scope.load = function(item) {
		Restangular.all('show/all/' + item.nNumber + '/' + item.seq).getList().then(function(collection){
			$scope.feeds = collection;
		})
	}
	
	/* call delete rest service*/
	$scope.remove = function (){
		if ($scope.select != undefined || $scope.select != null ){
			Restangular.all('delete/' + $scope.select.nNumber + '/' + $scope.select.seq).remove()
				.then(function(){
					init();
					$scope.unselect();
					$scope.feeds = [];
			})
		}
	}
	
	/* call create rest service */
	$scope.add = function() {
		Restangular.all('create').post($scope.feedProvider).then(
				function(newResource){
					init();
					clean();
				})
	}
	
	Restangular.setErrorInterceptor(
			function(response, deferred, responseHandler) {
				if(response.status != 200){
					if(response.data != null)
						$scope.addAlert(response.data.message);
				}
				return false; 
			}
	);
	
	$scope.addAlert = function(message) {
		$scope.alerts.push({ type: 'danger', msg: message });
	};

	$scope.closeAlert = function(index) {
	    $scope.alerts.splice(index, 1);
	};
	
}]); 

