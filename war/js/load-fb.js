window.fbAsyncInit = function() {
  FB.init({appId: '%s', status: true, cookie: true,
           xfbml: true});
  FB.getLoginStatus(function(response) {  });
  mungo.init(FB);
};
(function() {
  var e = document.createElement('script'); e.async = true;
  e.src = document.location.protocol +
    '//connect.facebook.net/en_US/all.js';
  document.getElementById('fb-root').appendChild(e);
}());
