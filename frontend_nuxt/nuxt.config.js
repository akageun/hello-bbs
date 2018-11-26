module.exports = {
  publicPath: '/',
  /*
  ** Headers of the page
  */
  head: {
    title: 'Hello, BBS',
    meta:
      [
        {charset: 'utf-8'},
        {name: 'viewport', content: 'width=device-width, initial-scale=1'},
        {hid: 'description', name: 'description', content: 'Nuxt.js project'}
      ],
    link:
      [
        {rel: 'icon', type: 'image/x-icon', href: '/favicon.ico'},
        {rel: 'stylesheet', href: 'https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css'}
      ],
    script:
      [
        {src: 'https://code.jquery.com/jquery-3.3.1.min.js', type: 'text/javascript'},
        {src: 'https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js', type: 'text/javascript'},
        {src: 'https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js', type: 'text/javascript'},
      ]

  }
  ,
  /*
  ** Customize the progress bar color
  */
  loading: {
    color: '#3B8070'
  },
  /*
  ** Build configuration
  */
  build: {
    vendor: [
      'simplemde', 'codemirror'
    ],
    /*
    ** Run ESLint on save
    */
    extend(config, {isDev, isClient}) {
      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    }
  }
}

