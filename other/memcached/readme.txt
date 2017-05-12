
brew info memcached

Required: libevent x

brew install libevent
brew install memcached

/usr/local/opt/memcached/bin/memcached -p 11211 -m 64m -vv
/usr/local/opt/memcached/bin/memcached -p 11211 -m 64m -d

telnet 127.0.0.1 11211
Trying 127.0.0.1...
Connected to localhost.
Escape character is '^]'.
set foo 0 0 3
bar
STORED
get foo
VALUE foo 0 3
bar
END


