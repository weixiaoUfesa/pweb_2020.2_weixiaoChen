import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Suponha que seu DataFrame seja df
# Exemplo:
df = pd.read_csv('sinan//exemplo.csv',sep=',',
    dtype={
        22: str,
        44: str,
        45: str,
        46: str,
        54: str
    })
print(df.head())
# Filtra apenas os casos da cidade de São Paulo e autóctones
df_sp_autoctone = df[(df['ID_MUNICIP'] == 120020) & (df['TPAUTOCTO'] == 1)]

# Exibe as primeiras linhas
print(df_sp_autoctone.head(30))
