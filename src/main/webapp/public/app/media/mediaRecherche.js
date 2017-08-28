angular
		.module('myApp')

		.controller(
				'MediaRechercheCtrl',
				[
						'$scope',
						'AdherentService',
						'$resource',
						'MediaService',
						'$rootScope',
						'$location',
						'orderByFilter',
						function($scope, AdherentService, $resource,
								MediaService, $rootScope, $location, orderBy) {

							 $scope.search={};
							$scope.adherents = {};
							$scope.disabled = true;
							$scope.search.titre = '';
							$scope.search.auteur = '';
							$scope.search.type = '';
							$scope.search.keyword = '';
							$scope.search.by = '';
							$scope.search.direction = '';
							$scope.infosPage = {}
							$scope.infosPage.page = 0;
							$scope.infosPage.size = '10';
							$scope.infosPage.totalPages = 0;
							$scope.infosPage.range = [ 1, 2, 3, 4, 5 ]
							$scope.isFirst = true;
							$scope.isLast = false;
							var idSort = false, auteurSort = false, titreSort = false, typeSort = false, empParSort = false, dateRetourPrevueSort = false;

							$scope.selected = '10';
							var page = 0;
							var size = 10;
							var totalPages;
							// $scope.range=[1,2,3,4,5];

							var getDatas = function(field, order, page, size) {
								MediaService.filter($scope.search.by,
										$scope.search.direction, page, size).$promise
										.then(function(result) {
											$scope.infosPage.totalPages = result.totalPages;
											$scope.infosPage.page = result.number;
											// $scope.infosPage.size=result.numberOfElements
											$scope.isLast = result.last;
											$scope.isFirst = result.first;
											fillArray();
											$scope.dataMedia = result.content;

											console
													.log($scope.infosPage.range.length);
											console.log(result)
										});
							}

							getDatas($scope.search.by, $scope.search.direction,
									$scope.infosPage.page,
									$scope.infosPage.size);

							$scope.change = function(index) {

								if ($scope.search.keyword) {

									$scope.searchAll(index);
								} else if ($scope.search.titre
										|| $scope.search.auteur || $scope.search.type) {
									$scope.seachBy(index);

								} else {
									// console.log("NO KEYWODRS")
									getDatas($scope.search.by,
											$scope.search.direction, index,
											$scope.infosPage.size)
								}
							}
							$scope.$watch('infosPage.size', function() {

								getDatas($scope.search.by,
										$scope.search.direction,
										$scope.infosPage.page,
										$scope.infosPage.size);

							}, true);



							$scope.previous=function(){
								$scope.change($scope.infosPage.page-1)
							}
							$scope.next=function(){
								$scope.change($scope.infosPage.page+1)
							}
							$scope.first=function(){
								$scope.change(0)
							}
							$scope.last=function(){
								$scope.change($scope.infosPage.totalPages-1)
							}

							/*
							 * MediaService.getByPage(page,size).$promise.then(function
							 * (result) { totalPages=result.totalPages;
							 * page=result.number; fillArray(); $scope.dataMedia
							 * =result.content;
							 * 
							 * console.log(result); });
							 */

							/*
							 * $scope.$watch('selected',function(){
							 * size=$scope.selected; console.log(size);
							 * MediaService.getByPage(page,size).$promise.then(function
							 * (result) { totalPages=result.totalPages;
							 * fillArray(); $scope.dataMedia =result.content;
							 *  })
							 * 
							 * 
							 * },true);
							 */

							function fillArray() {
								var tab = []

								for (var i = 0; i <$scope.infosPage.totalPages; i++) {
									tab[i] = i + 1;
								}
								$scope.infosPage.range = tab;

							}

							/*
							 * $scope.search={ titre:'', auteur:'', type:'',
							 *  }
							 */

							/*
							 * $scope.$watch('search.titre',function(){
							 * if(!$scope.search.titre){ $scope.search.titre='' }
							 *  //
							 * console.log($scope.adherent.cotisation.datePaiement)
							 * console.log($scope.search.keyword) },true);
							 * $scope.$watch('search.auteur',function(){
							 * if(!$scope.search.auteur){
							 * $scope.search.auteur='' }
							 *  //
							 * console.log($scope.adherent.cotisation.datePaiement)
							 * console.log($scope.search.keyword) },true);
							 * $scope.$watch('search.type',function(){
							 * if(!$scope.search.type){ $scope.search.type='' }
							 *  //
							 * console.log($scope.adherent.cotisation.datePaiement)
							 * console.log($scope.search.keyword) },true);
							 */
							// recupurer les données //
							// console.log($scope.dataMedia);
							/*
							 * MediaService.getMediaViaResource().$promise.then(function
							 * (result) { $scope.dataMedia = result.content;
							 * console.log($scope.dataMedia);
							 * angular.forEach($scope.dataMedia,function(value,key){
							 * //console.log(key + " " + value.dateNaissaince);
							 *  // console.log(value.dateNaissaince);
							 * //.log(key)
							 * 
							 * console.log(value); console.log(key)
							 * if(value.empruntEnCours){
							 * 
							 * $scope.dataMedia[key].dateRetourPrevue=new
							 * Date(value.empruntEnCours.adherent.dateRetourPrevue);
							 * console.log(value.dateRetourPrevue);
							 *  }
							 * 
							 * 
							 * });
							 * 
							 * 
							 * $scope.propertyName = ''; $scope.reverse = false;
							 * $scope.dataMedia = orderBy($scope.dataMedia,
							 * $scope.propertyName, $scope.reverse); });
							 */
							// / supprimer un media ///
							/*
							 * $scope.supprimerMedia = function (index) {
							 *  // console.log('je suis dans la function
							 * supprimer'); MediaService.removeMedia(index); };
							 */
							$scope.afficherMediaDetail = function(index) {
								var id;
								// console.log('dans la function afficher')
								angular.forEach($scope.dataMedia, function(
										value, key) {
									if (key === index) {
										id = value.id
										// $rootScope.media = value;

									}
								});
								$scope.redirect(id);
							};
							// redirection vers le detail du media
							$scope.redirect = function(id) {
								$location.path('/mediaDetail/' + id);
							}

							$scope.searchBy = function(index) {

								if ($scope.search.keyword) {
									$scope.search.keyword = '';
								}
								var page = 0;
								if (index) {
									page = index;
								}

								MediaService.getMediaBy($scope.search.titre,
										$scope.search.auteur,
										$scope.search.type, page,
										$scope.infosPage.size).$promise
										.then(function(result) {
											$scope.infosPage.totalPages=result.totalPages;
						                	$scope.infosPage.page=result.number;
						                //	$scope.infosPage.size=result.numberOfElements
						                	$scope.isLast=result.last;
						                	$scope.isFirst=result.first;
						                	 fillArray();
											// console.log(result);
											$scope.dataMedia = result.content;
										})
							}
							$scope.searchAll = function(index) {
								var page = 0;
								if (index) {
									page = index
								}
								if ($scope.search.auteur || $scope.search.titre
										|| $scope.search.type) {
									$scope.search.auteur = '';
									$scope.search.titre = '';
									$scope.search.type='';
								}

								MediaService.search($scope.search.keyword,
										page, $scope.infosPage.size).$promise
										.then(function(result) {
											// console.log("abcs")
											$scope.infosPage.totalPages = result.totalPages;
											$scope.infosPage.page = result.number;
											// $scope.infosPage.size=result.numberOfElements
											$scope.isLast = result.last;
											$scope.isFirst = result.first;
											fillArray();
											$scope.dataMedia = result.content;
										})

							}

							// ////// tri par id

							/*
							 * $scope.sortBy = function (propertyName) { //
							 * console.log(propertyName) $scope.reverse =
							 * (propertyName !== null && $scope.propertyName ===
							 * propertyName) ? !$scope.reverse : false;
							 * $scope.propertyName = propertyName;
							 * $scope.dataMedia = orderBy($scope.dataMedia,
							 * $scope.propertyName, $scope.reverse); };
							 */

							$scope.sortId = function() {
								idSort = (!idSort);
								// nomSort = (!nomSort);
								if (idSort === true) {
									MediaService.filter('id', 'asc',$scope.infosPage.page,$scope.infosPage.size).$promise
											.then(function(result) {
												$scope.dataMedia = result.content;
												$scope.search.by='id';
							            		$scope.search.direction='asc';
											})
								} else {
									MediaService.filter('id', 'desc',$scope.infosPage.page,$scope.infosPage.size).$promise
											.then(function(result) {
												$scope.dataMedia = result.content;
												$scope.search.by='id';
							            		$scope.search.direction='desc';
											})
								}

							};
							// TRI PAR NOM
							$scope.sortTitre = function() {
								auteurSort = (!auteurSort);
								if (auteurSort === true) {
									MediaService.filter('titre', 'asc',$scope.infosPage.page,$scope.infosPage.size).$promise
											.then(function(result) {
												$scope.dataMedia = result.content;
												$scope.search.by='titre';
							            			$scope.search.direction='asc';
											})
								} else {
									MediaService.filter('titre', 'desc',$scope.infosPage.page,$scope.infosPage.size).$promise
											.then(function(result) {
												$scope.dataMedia = result.content;
												$scope.search.by='titre';
							            		$scope.search.direction='desc';
											})
								}

							};
							// TRI PAR PRENOM
							$scope.sortAuteur = function() {
								titreSort = (!titreSort);
								if (titreSort === true) {
									MediaService.filter('auteur', 'asc',$scope.infosPage.page,$scope.infosPage.size).$promise
											.then(function(result) {
												$scope.dataMedia = result.content;
												$scope.search.by='auteur';
							            		$scope.search.direction='asc';
											})
								} else {
									MediaService.filter('auteur', 'desc',$scope.infosPage.page,$scope.infosPage.size).$promise
											.then(function(result) {
												$scope.dataMedia = result.content;
												$scope.search.by='id';
							            		$scope.search.direction='auteur';
											})
								}

							};
							// TRI PAR DATE DE NAISSANCE
							$scope.sortType = function() {
								typeSort = (!typeSort);
								if (typeSort === true) {

									MediaService.filter('type', 'asc',$scope.infosPage.page,$scope.infosPage.size).$promise
											.then(function(result) {
												$scope.dataMedia = result.content;
												$scope.search.by='type';
							            		$scope.search.direction='asc';
											})
								} else {
									MediaService.filter('type', 'desc',$scope.infosPage.page,$scope.infosPage.size).$promise
											.then(function(result) {
												$scope.dataMedia = result.content;
												$scope.search.by='type';
							            		$scope.search.direction='desc';
											})
								}
							};
							// TRI PAR LES ADHERENT QUI SONT A JOUR DANS LEUR
							// COTISATIONS OU PAS
							$scope.sortEmpruntePar = function() {
								empParSort = (!empParSort);
								if (empParSort === true) {
									MediaService.filter('emprunteepar', 'asc',$scope.infosPage.page,$scope.infosPage.size).$promise
											.then(function(result) {
												$scope.dataMedia = result.content;
												$scope.search.by='emprunteepar';
							            		$scope.search.direction='asc';
											})
								} else {
									MediaService.filter('emprunteepar', 'desc',$scope.infosPage.page,$scope.infosPage.size).$promise
											.then(function(result) {
												$scope.dataMedia = result.content;
												$scope.search.by='emprunteepar';
							            		$scope.search.direction='desc';
											})
								}
							};

							// TRI PAR NOMBRE DE MEDIA
							$scope.sortDateRetourPrevue = function() {
								dateRetourPrevueSort = (!dateRetourPrevueSort);
								if (dateRetourPrevueSort === true) {
									MediaService.filter('dateretourprevue',
											'asc',$scope.infosPage.page,$scope.infosPage.size).$promise.then(function(
											result) {
										$scope.dataMedia = result.content;
										$scope.search.by='dateretourprevue';
					            		$scope.search.direction='asc';
									})
								} else {
									MediaService.filter('dateretourprevue',
											'desc',$scope.infosPage.page,$scope.infosPage.size).$promise.then(function(
											result) {
										$scope.dataMedia = result.content;
										$scope.search.by='dateretourprevue';
					            		$scope.search.direction='desc';
									})
								}
							}

						} ]);