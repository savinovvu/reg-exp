`use strict`;

var gulp = require(`gulp`);
var plumber = require(`gulp-plumber`);
var sourcemap = require(`gulp-sourcemaps`);
var less = require(`gulp-less`);
var postcss = require(`gulp-postcss`);
var autoprefixer = require(`autoprefixer`);
var server = require(`browser-sync`).create();
var csso = require(`gulp-csso`);
var rename = require(`gulp-rename`);
var imagemin = require(`gulp-imagemin`);
var webp = require(`gulp-webp`);
var sprite = require(`gulp-svgstore`);
var posthtml = require(`gulp-posthtml`);
var include = require(`posthtml-include`);
var del = require(`del`);
var htmlmin = require(`gulp-htmlmin`);
var i18n = require(`gulp-html-i18n`);
var root = `../../infrastructure/nginx/src`;
var buildRootFolder = `${root}/landing`;
var src = `./landing/src`
var suffix = "-landing";


gulp.task(`css${suffix}`, function () {
  return gulp.src(`${src}/less/style.less`)
    .pipe(plumber())
    .pipe(sourcemap.init())
    .pipe(less())
    .pipe(postcss([
      autoprefixer()
    ]))

    .pipe(csso())
    .pipe(rename(`style.min.css`))

    .pipe(sourcemap.write(`.`))
    .pipe(gulp.dest(`${buildRootFolder}/css`))
    .pipe(server.stream());
});

gulp.task(`watch${suffix}`, function () {
  gulp.watch(`${src}/less/**/*.less`, gulp.series(`css${suffix}`));
  gulp.watch(`${src}/img/sprite-*.svg`, gulp.series(`sprite${suffix}`, `posthtml${suffix}`, `refresh`));
  gulp.watch(`${src}/js/**/*.js`, gulp.series(`copy-js${suffix}`, `refresh`));
  gulp.watch(`${src}/*.html`, gulp.series(`posthtml${suffix}`, `refresh`));
});



gulp.task(`imagemin`, function () {
  return gulp.src(`${src}/img/**/*.{png,jpg,svg}`)
    .pipe(imagemin([
      imagemin.gifsicle({ interlaced: true }),
      imagemin.jpegtran({ progressive: true }),
      imagemin.optipng({ optimizationLevel: 3 }),
      imagemin.svgo()
    ]))
    .pipe(gulp.dest(`${src}/img`))
});


gulp.task(`webp`, function () {
  return gulp.src(`${src}/img/**/*.{png,jpg}`)
    .pipe(webp({ quality: 90 }))

    .pipe(gulp.dest(`${src}/img`))
});

gulp.task(`sprite${suffix}`, function () {
  return gulp.src(`${src}/img/sprite-*.svg`)

    .pipe(sprite({
      inlineSvg: true
    }))
    .pipe(rename(`sprite.svg`))

    .pipe(gulp.dest(`${buildRootFolder}/img`))
});

gulp.task(`posthtml${suffix}`, function () {
  return gulp.src(`${src}/*.html`)

    .pipe(posthtml([
      include()
    ]))
    .pipe(i18n({
      langDir: `${src}/i18n`,
      renderEngine: 'mustache',
      defaultLang: 'en',
      createLangDirs: true
    }))
    .pipe(htmlmin({ collapseWhitespace: true }))

    .pipe(gulp.dest(`${buildRootFolder}`))
});

gulp.task(`copy-js${suffix}`, function () {
  return gulp.src([
    `${src}/js/**`
  ], {
    base: `${src}`
  })
    .pipe(gulp.dest(`${buildRootFolder}`));
});


gulp.task(`copy`, function () {
  return gulp.src([
    `${src}/fonts/**/*.{woff,woff2}`,
    `${src}/img/**`,
    `${src}/js/**`
  ], {
    base: `${src}`
  })
    .pipe(gulp.dest(`${buildRootFolder}`));
});

gulp.task(`clean`, function () {
  return del(`${buildRootFolder}`, { force: true });
});

gulp.task(`gulp-htmlmin`, function () {
  return gulp.src(`${buildRootFolder}/src/*.html`)
    .pipe(htmlmin({ collapseWhitespace: true }))
    .pipe(gulp.dest(`build`));
});


gulp.task(`buildLanding`, gulp.series(`clean`, `copy`, `css${suffix}`, `sprite${suffix}`, `posthtml${suffix}`));
