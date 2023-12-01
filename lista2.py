import numpy as np
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
df = pd.read_csv('hhh.csv')

import numpy as np
import matplotlib.pyplot as plt
plt.scatter(df['altura'],df['peso'])
plt.axline([0,-165.608731],slope=139.3910101)


plt.xticks(np.arange( 0,3.5, 0.5)) 
plt.yticks(np.arange(0, 125, 10)) 
plt.ylim(0,125)
plt.xlim(0,3.5)
plt.xlabel("altura")
plt.ylabel("peso")
plt.title("dispersão")
plt.grid()



#sns.lmplot(data=df,x=(0,5),y='peso',order=0.1,truncate=False)
plt.show()