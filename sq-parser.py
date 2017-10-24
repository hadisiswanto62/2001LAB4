import csv

import json


def get_data():
    with open('SQ-openflights.csv', "rt", encoding="utf-8") as file:
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
                from_code = row[0][4:7]
                to_code = row[0][8:11]
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
                routes[from_city].append(to_city)
                # print(from_city, from_country, to_city, to_country)
            # print(json.dumps(routes, indent=4))
            with open('airports-name.json', 'w') as temp:
                temp.write(json.dumps(countries, indent=4))
            with open('SQ-routes.json', 'w') as temp:
                temp.write(json.dumps(routes, indent=4))

get_data()
