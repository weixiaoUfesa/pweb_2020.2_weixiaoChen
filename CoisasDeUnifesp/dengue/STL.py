import pandas as pd
import matplotlib.pyplot as plt
import statsmodels
from statsmodels.tsa.seasonal import STL
print("Statsmodels funcionando corretamente!")

# 1. 读取 CSV 文件
df = pd.read_csv("resumo_dengue_2014_2023.csv")

# 2. 清理数据：删除空行，转换为整数
df = df.dropna()
df['ANO'] = df['ANO'].astype(int)
df['SEMANA'] = df['SEMANA'].astype(int)

# 3. 创建每周的日期（每年的第几周的周一）
df["DATA"] = pd.to_datetime(
    df["ANO"].astype(str) + df["SEMANA"].astype(str).str.zfill(2) + "1",
    format="%G%V%u"
)

# 4. 设置时间索引
df.set_index('DATA', inplace=True)
df.sort_index(inplace=True)

# 5. 创建病例数时间序列（每周）
weekly_series = df['Total_casos'].asfreq('W-MON')
print(weekly_series.isna().sum())
# 6. 应用 STL 分解
stl = STL(weekly_series, seasonal=53, robust=True)
result = stl.fit()

# 7. 可视化
fig = result.plot()
fig.set_size_inches(12, 8)
fig.suptitle("登革热病例 STL 分解（每周数据）", fontsize=16)
plt.tight_layout()
plt.show()
