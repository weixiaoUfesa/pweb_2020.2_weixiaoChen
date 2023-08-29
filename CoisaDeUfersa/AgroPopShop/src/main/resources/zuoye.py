import heapq
import math

pqueue=[]
grafico={
      "A":{"B":8,"C":14},
      "B":{"D":38},
      "C":{"B":9,"E":7,"D":24},
      "D":{"G":9},
      "E":{"D":13,"G":29,"F":9},
      "F":{"F":0},
      "G":{"G":0}}
    
      
     

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

parent,distancia=dijkstra(grafico)
print(parent)
print(distancia)


def busca(str1):
    print("custo:",distancia[str1])
    print(str1)
    while(str1!="A"):
        str1=parent[str1]
        print("↑")
        print(str1)
        
        
str1=input('digite destino:')   
busca(str1)                     
