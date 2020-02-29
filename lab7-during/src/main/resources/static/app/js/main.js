var wafepaApp = angular.module("wafepaApp", ["ngRoute"]);

wafepaApp.controller("HomeCtrl", function($scope){
	$scope.message = "Hello JWD42!";
});

wafepaApp.controller("ActivitiesCtrl", function($scope, $http, $location){
	
	var url = "/api/activities";
	
	$scope.activities = [];
	
	var getActivities = function(){
		var promise = $http.get(url);
		promise.then(
			function success(res){
				//console.log(res);
				$scope.activities = res.data;
			},
			function error(res){
				alert("Couldn't fetch activities");
			}
		);
		
		//console.log("test");
	}
	
	getActivities();
	
	$scope.goToEdit = function(id){
		$location.path("/activities/edit/" + id);
	}

});


wafepaApp.controller("EditActivityCtrl", function($scope, $routeParams, $http, $location){
	//console.log($routeParams);
	var url = "/api/activities/" + $routeParams.id;
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	var getActivity = function(){
		$http.get(url).then(
			function uspeh(odg){
				$scope.activity = odg.data;
			},
			function neuspeh(){
				alert("Couldn't fetch the activities");
			}
		);
	}
		
	getActivity();
	
	
	$scope.doEdit = function(){
		$http.put(url, $scope.activity).then(
			function success(){
				$location.path("/activities");
			},
			function error(){
				alert("Couldn't save the activity.");
			}
		);
	}
});



wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: 'HomeCtrl'
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/edit/:id', {
			templateUrl : '/app/html/edit-activity.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);