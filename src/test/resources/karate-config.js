//https://github.com/karatelabs/karate

function fn() {
  var env = karate.env; // get java system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev'; // a custom 'intelligent' default
  }
  var config = { // base config JSON
    urlBase: 'https://rickandmortyapi.com/api/',
    anotherUrlBase: 'https://another-host.com/v1/'
  };
  if (env == 'qa') {
    // over-ride only those that need to be
    config.someUrlBase = 'https://rickandmortyapi-qa.com/api/';
  } else if (env == 'pdn') {
    config.someUrlBase = 'https://rickandmortyapi-pdn.com/api/';
  }
  // don't waste time waiting for a connection or if servers don't respond within 5 seconds
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  return config;
}