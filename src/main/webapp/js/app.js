var StudentApp = angular.module("StudentApp", ['ngResource', 'ngRoute']).
					config(function($routeProvider){
						$routeProvider.
							when('/', { controller: MainCtrl, templateUrl: 'html/main.html' }).
							when('/list', { controller: ListCtrl, templateUrl: 'html/list.html' }).
							when('/add', { controller: CreateCtrl, templateUrl: 'html/details.html' }).
							when('/edit/:studentId', { controller: EditCtrl, templateUrl: 'html/details.html' }).
							otherwise( { redirectTo: '/'} );
					});

StudentApp.factory('Student', function($resource){
	return $resource('rest/StudentApp/:id', { id: '@id' }, { update: { method: 'PUT' } });
});

var MainCtrl = function($scope, $location){
	$scope.appName="Student App";
};

var ListCtrl = function($scope, $location, Student){
	$scope.reset = function(){
		$scope.students=Student.query({q: $scope.query});
	};
	
	$scope.deleteStudent = function() {
		var studentId = this.student.id;
		Student.delete({id: studentId}, function(){
			$("#student_" + studentId).fadeOut();
		});
	};
	
	$scope.reset();
};

var CreateCtrl = function($scope, $location, Student){
	$scope.action = "Add";
	$scope.save = function(){
		Student.save($scope.student, function(){
			$location.path("/");
		});
	};
};

var EditCtrl = function($scope, $routeParams, $location, Student){
	$scope.student=Student.get({ id: $routeParams.studentId });
	$scope.action = "Update";
	$scope.save = function(){
		Student.update({ id:$scope.student.id }, $scope.student, function(){
			$location.path("/");
		});
	};
};
