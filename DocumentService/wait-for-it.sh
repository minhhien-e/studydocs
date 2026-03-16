##!/usr/bin/env bash
#host="$1"
#shift
#port="$1"
#shift
#
## mặc định timeout 30 giây
#timeout=30
#
## nếu truyền --timeout=XX thì cập nhật timeout
#for arg in "$@"; do
#  if [[ "$arg" =~ ^--timeout=([0-9]+)$ ]]; then
#    timeout="${BASH_REMATCH[1]}"
#    # xóa tham số này khỏi danh sách command
#    set -- "${@/$arg/}"
#  fi
#done
#
#echo "⏳ Waiting for $host:$port to be ready..."
## nc với OpenBSD netcat dùng -z -w <timeout>
#while ! nc -z -w "$timeout" "$host" "$port"; do
#  sleep 1
#done
#
#echo "✅ $host:$port is ready — starting app..."
#exec "$@"
