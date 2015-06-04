from __future__ import print_function

import requests

try:
    input = raw_input
except NameError:
    pass

try:
    from urllib import urlopen, urlencode
except ImportError:
    from urllib.parse import urlencode
    from urllib.request import urlopen


class Req:
    class Res:
        def __init__(self, text):
            self.text = text.decode()

    def post(self, url, data):
        return self.Res(urlopen(url + '?' + urlencode(data)).read())


try:
    import requests
except ImportError:
    requests = Req()


q = input('Online? [Y/N] ')
BASE = (
    'http://localhost:9000' if q.lower() in {'n', 'nay', 'no'}
    else 'https://yoaspike.herokuapp.com'
)

r = requests.post(
    BASE + '/accounts/add_user',
    data={
        'email': input('email: '),
        'password': input('password: ')
    }
)
print(r)
if len(r.text) < 200:
    print(r.text)
