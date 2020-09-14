"use strict";
require("./landing/gulpfile")
require("./auth/sign-up/gulpfile")
require("./auth/login/gulpfile")

var gulp = require("gulp");
var server = require("browser-sync").create();
var root = "../../infrastructure/nginx/src";


gulp.task("server", function () {

  server.init({
    server: root,
    notify: false,
    open: true,
    cors: true,
    ui: false,
    port: 3000
  });

  gulp.series("watch-landing")();
  gulp.series("watch-login")();
  gulp.series("watch-signUp")();
  // gulp.watch(`./landing/src/*.html`, gulp.series("build", "refresh"));

});


gulp.task("refresh", function (done) {
  console.log("============================================sdfsd");
  server.reload();
  done();
});






gulp.task("build", gulp.parallel("buildLanding", "buildSignUp", "buildLogin"));
gulp.task("start", gulp.series("build", "server"));


