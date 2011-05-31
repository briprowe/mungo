(ns mungo.core
  (:use [hiccup.core]
        [compojure.core])
  (:require [appengine-magic.core :as ae]))

(defroutes all
  (ANY "*" []  (html [:html [:body    "<div id=\"fb-root\"></div>
<script>
  window.fbAsyncInit = function() {
    FB.init({appId: 'your app id', status: true, cookie: true,
             xfbml: true});
  };
  (function() {
    var e = document.createElement('script'); e.async = true;
    e.src = document.location.protocol +
      '//connect.facebook.net/en_US/all.js';
    document.getElementById('fb-root').appendChild(e);
  }());
</script>"]])))

(ae/def-appengine-app mungo-app #'all)
