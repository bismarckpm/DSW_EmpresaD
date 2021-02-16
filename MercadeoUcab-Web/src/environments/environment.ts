// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
};

export const GLOBAL = {
  //urlOscar: 'http://localhost:8080/servicio-1.0-SNAPSHOT/api/v1',
  //urlOscar: 'http://45.76.60.252:8080/mercadeoucab-1.0-SNAPSHOT/api/v1',
  urlOscar:'http://ec2-18-189-185-99.us-east-2.compute.amazonaws.com/mercadeoucab-1.0-SNAPSHOT/api/v1',
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
