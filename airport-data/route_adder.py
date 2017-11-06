import json
from random import randint

def getRandomCity(airportsId, leng):
    roll = randint(0, leng)
    for i in airportsId:
        if airportsId[i] == roll:
            return i

def getNextCity(selectedCity, airportsId,data, leng):
    ix = 0
    targetCity = ""
    for i in airportsId:
        if airportsId[i] == ix:
            targetCity = i
    while targetCity in data[selectedCity]:
        ix += 1
        for i in airportsId:
            if airportsId[i] == ix:
                targetCity = i
        if targetCity not in data[selectedCity]:
            return targetCity
    return "";
    
full = ['asd']
a = "WN"
nodeCount = 0
edgeCount = 0
data = ''
with open('routes-'+a+'.json') as f:
    data = json.load(f)
    for i in data:
        nodeCount += 1
        edgeCount += len(data[i])
airportsId = {}
with open('airports-name-'+a+'.json') as f:
    airportsId = json.load(f)

while True:
    print(nodeCount, edgeCount)
    targetEdge = int(input())

    while (edgeCount!=targetEdge):
        selectedCity = 'asd'
        while selectedCity in full:
            selectedCity = getRandomCity(airportsId, len(data) - 1)
        targetCity = selectedCity
        count = 0
        while (targetCity == selectedCity) or (targetCity in data[selectedCity]):
            if count == 10:
                targetCity = getNextCity(selectedCity,airportsId,data, len(data) - 1)
                break
            targetCity = getRandomCity(airportsId, len(data) - 1)
            count += 1
        if targetCity == "":
            continue
        data[selectedCity].append(targetCity)
        if len(data[selectedCity]) == nodeCount-1:
               full.append(selectedCity)
        edgeCount+=1

    with open('routes-'+a+'-'+str(nodeCount)+'-'+str(edgeCount)+'.json', 'w') as temp:
        temp.write(json.dumps(data))


