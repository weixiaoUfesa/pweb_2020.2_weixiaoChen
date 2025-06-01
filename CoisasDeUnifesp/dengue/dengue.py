import pandas as pd

# Lê os arquivos CSV
df1 = pd.read_csv('media_de_variacao.csv', encoding='latin1')  # contém coluna 'data'
df2 = pd.read_csv('media_de_temperatura.csv', encoding='latin1')  # contém coluna 'data'
df3 = pd.read_csv('soma_de_chuva.csv', encoding='latin1')  # contém coluna 'data'


# Converte a coluna 'data' para datetime (opcional mas recomendado)
for df in [df1, df2, df3]:
    df['data'] = pd.to_datetime(df['data'])

# Junta os 5 DataFrames pela coluna 'data'
df_merged = df1.merge(df2, on='data', how='outer') \
               .merge(df3, on='data', how='outer') \
            

# Exibe as primeiras linhas
print(df_merged.head())

# (Opcional) Salva o resultado em um novo arquivo
df_merged.to_csv('dados_unificados.csv', index=False)

# Carrega seu CSV (ajuste encoding e sep conforme seu arquivo)
df = pd.read_csv('dados_unificados.csv', encoding='latin1')
print(df['data'].head(10))
# Converte coluna 'data' para datetime
df['data'] = pd.to_datetime(df['data'])


print(df['data'].head(10))

# Extrai a semana do ano (1 a 53) da data
df['semana'] = df['data'].dt.isocalendar().week

# Agrupa por semana e soma as variáveis desejadas
df_semana = df.groupby('semana').agg({

 'media_amplitude_temp': 'mean',        # média
    'media_amplitude_orvalho': 'mean',     # média
    'media_amplitude_umidade': 'mean',     # média
    'max_temp_bulbo': 'max',                # máximo
    'media_temp_orvalho': 'mean',           # média
    'soma_precipitacao': 'sum'              # soma
}).reset_index()

print(df_semana)


# Lê os arquivos CSV
import pandas as pd
df1 = pd.read_csv('dados_agrupados_semana.csv', encoding='utf-8')  # contém coluna 'data'
df2 = pd.read_csv('denguebsp2023_semanas.csv', encoding='utf-8')  # contém coluna 'data'

print(df1.columns)
print(df2.columns)


# Converte a coluna 'data' para datetime (opcional mas recomendado)

# Junta os 5 DataFrames pela coluna 'data'
df_merged = df1.merge(df2, on='semana', how='outer') \
             
            

# Exibe as primeiras linhas
print(df_merged.head())

# (Opcional) Salva o resultado em um novo arquivo
df_merged.to_csv('semana.csv', index=False)