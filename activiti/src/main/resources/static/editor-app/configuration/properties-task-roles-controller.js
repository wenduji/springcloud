/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Form Properties
 */

var KisBpmTaskRolesCtrl = ['$scope', '$modal', '$timeout', '$translate', function ($scope, $modal, $timeout, $translate) {

    // Config for the modal window
    var opts = {
        template: 'editor-app/configuration/properties/task-roles-popup.html?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    $modal(opts);
}];

var KisBpmTaskRolesPopupCtrl = ['$scope', '$q', '$translate', '$timeout', '$http', function ($scope, $q, $translate, $timeout, $http) {

    // Put json representing form properties on scope
    if ($scope.property.value !== undefined && $scope.property.value !== null
        && $scope.property.value.taskRoles !== undefined
        && $scope.property.value.taskRoles !== null) {
        // Note that we clone the json object rather then setting it directly,
        // this to cope with the fact that the user can click the cancel button and no changes should have happended
        $scope.taskRoles = angular.copy($scope.property.value.taskRoles);

        for (var i = 0; i < $scope.taskRoles.length; i++) {
            var formProperty = $scope.taskRoles[i];
            if (formProperty.enumValues && formProperty.enumValues.length > 0) {
                for (var j = 0; j < formProperty.enumValues.length; j++) {
                    var enumValue = formProperty.enumValues[j];
                    if (!enumValue.id && !enumValue.name && enumValue.value) {
                        enumValue.id = enumValue.value;
                        enumValue.name = enumValue.value;
                    }
                }
            }
        }

    } else {
        $scope.taskRoles = [];
    }

    // Array to contain selected properties (yes - we only can select one, but ng-grid isn't smart enough)
    $scope.selectedProperties = [];
    $scope.selectedEnumValues = [];

    $scope.translationsRetrieved = false;

    $scope.labels = {};

    var noPromise = $translate('PROPERTY.TASK_ROLES.NO');
    var rolePromise = $translate('PROPERTY.TASK_ROLES.FIELDS.ROLE');

    $q.all([noPromise, rolePromise]).then(function (results) {
        $scope.labels.noLabel = results[0];
        $scope.labels.roleLabel = results[1];
        $scope.translationsRetrieved = true;

        // Config for grid
        $scope.gridOptions = {
            data: 'taskRoles',
            enableRowReordering: true,
            headerRowHeight: 28,
            multiSelect: false,
            keepLastSelected: false,
            selectedItems: $scope.selectedProperties,
            columnDefs: [{field: 'id', displayName: $scope.labels.noLabel},
                // {field: 'name', displayName: $scope.labels.nameLabel},
                {field: 'type', displayName: $scope.labels.roleLabel}]
        };

        $scope.enumGridOptions = {
            data: 'selectedProperties[0].enumValues',
            enableRowReordering: true,
            headerRowHeight: 28,
            multiSelect: false,
            keepLastSelected: false,
            selectedItems: $scope.selectedEnumValues,
            columnDefs: [{field: 'id', displayName: $scope.labels.noLabel}]
        }
    });

    // Handler for when the value of the type dropdown changes
    $scope.propertyTypeChanged = function () {

        // Check date. If date, show date pattern
        if ($scope.selectedProperties[0].type === 'date') {
            $scope.selectedProperties[0].datePattern = 'MM-dd-yyyy hh:mm';

        } else {
            delete $scope.selectedProperties[0].datePattern;
        }

        // Check enum. If enum, show list of options
        if ($scope.selectedProperties[0].type === 'enum') {
            $scope.selectedProperties[0].enumValues = [
                {
                    id: 'value1',
                    name: 'Value 1'
                },
                {
                    id: 'value2',
                    name: 'Value 2'
                }];
        } else {
            delete $scope.selectedProperties[0].enumValues;
        }
    };

    // Click handler for add button
    var index = 1;
    $scope.addNewRole = function () {
        $http({
            method: 'GET',
            url: '/rest/roles'
        }).then(function successCallback(response) {
            // 请求成功执行代码
            // 角色下拉框选项
            var roles = response.data.data;
            $scope.options = roles;

            $scope.taskRoles.push({
                id: index++,
                type: roles !== null ? roles[0] : '',
                readable: true,
                writable: true
            });

            $timeout(function () {
                $scope.gridOptions.selectItem($scope.taskRoles.length - 1, true);
            });
        }, function errorCallback(response) {
            // 请求失败执行代码
            alert(response);
        });
    };

    // Click handler for remove button
    $scope.removeRole = function () {
        if ($scope.selectedProperties.length > 0) {
            var index = $scope.taskRoles.indexOf($scope.selectedProperties[0]);
            $scope.gridOptions.selectItem(index, false);
            $scope.taskRoles.splice(index, 1);

            $scope.selectedProperties.length = 0;
            if (index < $scope.taskRoles.length) {
                $scope.gridOptions.selectItem(index + 1, true);
            } else if ($scope.taskRoles.length > 0) {
                $scope.gridOptions.selectItem(index - 1, true);
            }
        }
    };

    // Click handler for up button
    $scope.movePropertyUp = function () {
        if ($scope.selectedProperties.length > 0) {
            var index = $scope.taskRoles.indexOf($scope.selectedProperties[0]);
            if (index != 0) { // If it's the first, no moving up of course
                // Reason for funny way of swapping, see https://github.com/angular-ui/ng-grid/issues/272
                var temp = $scope.taskRoles[index];
                $scope.taskRoles.splice(index, 1);
                $timeout(function () {
                    $scope.taskRoles.splice(index + -1, 0, temp);
                }, 100);

            }
        }
    };

    // Click handler for down button
    $scope.movePropertyDown = function () {
        if ($scope.selectedProperties.length > 0) {
            var index = $scope.taskRoles.indexOf($scope.selectedProperties[0]);
            if (index != $scope.taskRoles.length - 1) { // If it's the last element, no moving down of course
                // Reason for funny way of swapping, see https://github.com/angular-ui/ng-grid/issues/272
                var temp = $scope.taskRoles[index];
                $scope.taskRoles.splice(index, 1);
                $timeout(function () {
                    $scope.taskRoles.splice(index + 1, 0, temp);
                }, 100);

            }
        }
    };

    $scope.addNewEnumValue = function () {
        if ($scope.selectedProperties.length > 0) {
            $scope.selectedProperties[0].enumValues.push({id: '', name: ''});
        }

        $timeout(function () {
            $scope.enumGridOptions.selectItem($scope.selectedProperties[0].enumValues.length - 1, true);
        });
    };

    // Click handler for remove button
    $scope.removeEnumValue = function () {
        if ($scope.selectedProperties.length > 0 && $scope.selectedEnumValues.length > 0) {
            var index = $scope.selectedProperties[0].enumValues.indexOf($scope.selectedEnumValues[0]);
            $scope.enumGridOptions.selectItem(index, false);
            $scope.selectedProperties[0].enumValues.splice(index, 1);

            $scope.selectedEnumValues.length = 0;
            if (index < $scope.selectedProperties[0].enumValues.length) {
                $timeout(function () {
                    $scope.enumGridOptions.selectItem(index + 1, true);
                });

            } else if ($scope.selectedProperties[0].enumValues.length > 0) {
                $timeout(function () {
                    $scope.enumGridOptions.selectItem(index - 1, true);
                });
            }
        }
    };

    // Click handler for up button
    $scope.moveEnumValueUp = function () {
        if ($scope.selectedProperties.length > 0 && $scope.selectedEnumValues.length > 0) {
            var index = $scope.selectedProperties[0].enumValues.indexOf($scope.selectedEnumValues[0]);
            if (index != 0) { // If it's the first, no moving up of course
                // Reason for funny way of swapping, see https://github.com/angular-ui/ng-grid/issues/272
                var temp = $scope.selectedProperties[0].enumValues[index];
                $scope.selectedProperties[0].enumValues.splice(index, 1);
                $timeout(function () {
                    $scope.selectedProperties[0].enumValues.splice(index + -1, 0, temp);
                });

            }
        }
    };

    // Click handler for down button
    $scope.moveEnumValueDown = function () {
        if ($scope.selectedProperties.length > 0 && $scope.selectedEnumValues.length > 0) {
            var index = $scope.selectedProperties[0].enumValues.indexOf($scope.selectedEnumValues[0]);
            if (index != $scope.selectedProperties[0].enumValues.length - 1) { // If it's the last element, no moving down of course
                // Reason for funny way of swapping, see https://github.com/angular-ui/ng-grid/issues/272
                var temp = $scope.selectedProperties[0].enumValues[index];
                $scope.selectedProperties[0].enumValues.splice(index, 1);
                $timeout(function () {
                    $scope.selectedProperties[0].enumValues.splice(index + 1, 0, temp);
                });

            }
        }
    };

    // Click handler for save button
    $scope.save = function () {

        if ($scope.taskRoles.length > 0) {
            $scope.property.value = {};
            $scope.property.value.taskRoles = $scope.taskRoles;
        } else {
            $scope.property.value = null;
        }

        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

    $scope.cancel = function () {
        $scope.$hide();
        $scope.property.mode = 'read';
    };

    // Close button handler
    $scope.close = function () {
        $scope.$hide();
        $scope.property.mode = 'read';
    };

}];