var mungo = (function  ($) {
  var access_token = null;

  var check_login = function(FB, app_id) {
      FB.getLoginStatus(function(response) {
        if( response.session ) {
          alert("here");
          var list = $("<ul></ul>")
          for( var member in response ) {
            $("<li>" + member + ": " + response[member] + "</li>").appendTo(list);
          }
          list.appendTo("body");

          get_access_token(response.session);
        } else {
          login(app_id);
        }
      });
    };

  var get_access_token = function(session) {
    var token = session.access_token;
    if( token ) {
      $('<p>Got access token: ' + token + "</p>").appendTo("body");
    } else {
      $("<p>Failed to get access token</p>").appendTo("body");
    }
  };

  var login = function(app_id) {
    var path = 'https://www.facebook.com/dialog/oauth?';
    var queryParams = ['client_id=' + app_id,
                       'redirect_uri=' + "http://apps.facebook.com/mungojerrie/canvas/",
                       "scope=" + "user_photos,publish_stream",
                       'response_type=token'];
    var query = queryParams.join('&');
    var url = path + query;
    window.top.location.href = url;
  };

  return { 
    init: function(FB, app_id) {
      check_login(FB, app_id);
    }};
})(jQuery);
