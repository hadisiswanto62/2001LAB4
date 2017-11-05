import csv

import json


def get_data():
    a ="AK"
    with open('openflights-'+a+'.csv', "rt", encoding="utf-8") as file:
        with open('./airport-codes/airports.json', "rt", encoding="utf-8") as data_file:
            data = json.load(data_file)
            reader = csv.reader(file)
            countries = {}
            routes = {}
            i = m = 0
            for row in reader:
                i += 1
                if(i%2==1):
                    continue
                from_code = row[1]
                to_code = row[2]
                #print(from_code, to_code)
                from_city = data[from_code]['city']
                # from_country = data[from_code]['country']
                to_city = data[to_code]['city']
                # to_country = data[to_code]['country']
                if not (from_city in countries.keys()):
                    countries[from_city] = m
                    m += 1
                if not(to_city in countries.keys()):
                    countries[to_city] = m
                    m += 1
                if not(from_city in routes.keys()):
                    routes[from_city] = []
                if not(to_city in routes.keys()):
                    routes[to_city] = []
                if (to_city not in routes[from_city]):
                    routes[from_city].append(to_city)
                if (from_city not in routes[to_city]):
                    routes[to_city].append(from_city)                # print(from_city, from_country, to_city, to_country)
            # print(json.dumps(routes, indent=4))
            with open('airports-name-'+a+'.json', 'w') as temp:
                temp.write(json.dumps(countries))
            with open('routes-'+a+'.json', 'w') as temp:
                temp.write(json.dumps(routes))

get_data()
