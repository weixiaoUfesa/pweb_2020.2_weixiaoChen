import heapq
import math

pqueue=[]
grafico={
      "A":{"B":5,"C":1},
      "B":{"A":5,"C":2,"D":1},
      "C":{"A":1,"B":2,"C":4,"D":8},
      "D":{"B":1,"C":4,"E":3,"F":6},
      "E":{"C":8,"D":3},
      "F":{"D":6}}

def iniciaDis(graph):
      distancia={"A":0}
      for vertex in grafico:
            if vertex !="A":
                  distancia[vertex]=math.inf
      return distancia  

def dijkstra(grafico):
      pqueue=[]
      heapq.heappush(pqueue,(0,"A"))
      seen=set()
   
      parent={"A":None}
      distancia=iniciaDis(grafico)
      while(len(pqueue)>0):
            par=heapq.heappop(pqueue)
            dis=par[0]
            vertex=par[1]
            seen.add("A")
            nodes=grafico[vertex].keys()
            for w in nodes:
                  if w not in seen:
                       if dis+grafico[vertex][w]<distancia[w]:
                             heapq.heappush(pqueue,(dis+grafico[vertex][w],w))
                             parent[w]=vertex
                             distancia[w]=dis+grafico[vertex][w]
                      
      return parent,distancia

parent,distanciaa=dijkstra(grafico)
print(parent)
print(distanciaa)

        


                        
