openssl req -x509 -out /etc/ssl/certs/nginx-selfsigned.crt -keyout /etc/ssl/certs/nginx-selfsigned.key \
  -newkey rsa:2048 -nodes -sha256 \
  -subj '/CN=localhost'
