#!/usr/bin/env bash

cd "$(dirname "$0")"
HAPROXY_DIR="$(pwd)"

DESTDIR="$HAPROXY_DIR"/build

if [[ $(uname -o) = "FreeBSD" ]]; then
    cd ./source || exit 2
    gmake -j "$(sysctl -n hw.ncpu)" TARGET=freebsd \
        USE_OPENSSL=1 USE_QUIC=1 USE_QUIC_OPENSSL_COMPAT=1 \
        USE_PCRE2=1  DESTDIR="$DESTDIR" || exit 3
    chmod u+x "$HAPROXY_DIR/source/haproxy"
else
    # PCRE2_INSTALL="$HAPROXY_DIR"/pcre2/install
    # cd ./pcre2 || exit 2
    # rm -rf "$PCRE2_INSTALL" || true
    # ./configure --disable-shared --prefix="$PCRE2_INSTALL" || exit 3
    # make -j "$(nproc)" || exit 3
    # make install || exit 3
    # cd ../

    OPENSSL_INSTALL="$HAPROXY_DIR"/openssl/install
    cd ./openssl || exit 2
    ./Configure --prefix="$OPENSSL_INSTALL" || exit 4
    make -j "$(nproc)" || exit 4
    make install_sw -j "$(nproc)" || exit 4 
    cd ../

    # MUSL_INSTALL="$HAPROXY_DIR"/musl/install
    # cd ./musl || exit 2
    # rm -rf "$MUSL_INSTALL" || true
    # ./configure --disable-shared --prefix="$MUSL_INSTALL" || exit 5
    # make -j "$(nproc)" || exit 5
    # make install || exit 5
    # cd ../

    cd ./source || exit 2
    make clean
    # make -j "$(nproc)" TARGET=linux-musl \
    #     CC="$MUSL_INSTALL"/bin/musl-gcc ADDINC="-I $MUSL_INSTALL/include" ADDLIB="-L $MUSL_INSTALL/lib" \
    #     USE_OPENSSL=1 SSL_INC="$OPENSSL_INSTALL"/include SSL_LIB="$OPENSSL_INSTALL"/lib64 \
    #     USE_QUIC=1 USE_QUIC_OPENSSL_COMPAT=1 \
    #     USE_STATIC_PCRE2=1 PCRE2_INC="$PCRE2_INSTALL"/include PCRE2_LIB="$PCRE2_INSTALL"/lib \
    #     DESTDIR="$DESTDIR" || exit 6
    make -j "$(nproc)" TARGET=linux-glibc USE_OPENSSL=1 \
        SSL_LIB="$OPENSSL_INSTALL"/lib64 SSL_INC="$OPENSSL_INSTALL"/include \
        USE_LIBCRYPT=0 || exit 6
    make -j "$(nproc)" install DESTDIR="$DESTDIR" || exit 6
    chmod u+x "$HAPROXY_DIR/build/usr/local/sbin/haproxy"
fi

