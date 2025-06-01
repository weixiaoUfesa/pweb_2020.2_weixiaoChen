import pandas as pd
import matplotlib.pyplot as plt
import statsmodels
from statsmodels.tsa.seasonal import STL
print("Statsmodels funcionando corretamente!")

# 1. 读取 CSV 文件
path=r'E:\dengue\dengue_dados\dengue.csv'
df = pd.read_csv(path)

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
plt.rcParams['font.family'] = 'Microsoft YaHei'  # 或 SimHei
plt.rcParams['axes.unicode_minus'] = False  # 避免负号显示为方块
fig.suptitle("登革热病例 STL 分解（每周数据）", fontsize=16)
plt.tight_layout()
plt.show()



import numpy as np
import matplotlib.pyplot as plt
from scipy.signal import find_peaks

# 1. 提取季节性分量并去除空值
#seasonal = result.seasonal.dropna()

seasonal = result.seasonal.dropna()
# 2. 进行快速傅里叶变换（FFT）
fft_result = np.fft.fft(seasonal)

# 3. 计算频率和振幅
n = len(seasonal)
freq = np.fft.fftfreq(n, d=1)  # 每周采样一次
amplitude = np.abs(fft_result)

# 4. 只保留前一半频率（由于对称性）
half = n // 2
freq = freq[:half]
amplitude = amplitude[:half]

# 5. 只保留 freq > 0 的部分，避免除以 0
valid_mask = freq > 0
valid_freq = freq[valid_mask]
valid_amplitude = amplitude[valid_mask]

# 6. 使用 find_peaks 找出最大 6 个峰值
peaks, _ = find_peaks(valid_amplitude, distance=2)
top_peaks = peaks[np.argsort(valid_amplitude[peaks])[-6:]]

# 7. 打印主周期信息
print("找到的主要周期（单位：周）：")
for idx in sorted(top_peaks, key=lambda x: 1/valid_freq[x]):
    freq_val = valid_freq[idx]
    cycle_weeks = 1 / freq_val
    amp_val = valid_amplitude[idx]
    print(f"- 周期约为 {cycle_weeks:.2f} 周（频率 = {freq_val:.4f}, 振幅 = {amp_val:.2f}）")

# 8. 可视化频谱并标注主周期
plt.rcParams['font.family'] = 'Microsoft YaHei'  # 中文支持
plt.rcParams['axes.unicode_minus'] = False

plt.figure(figsize=(12, 6))
plt.plot(1 / valid_freq, valid_amplitude, label='频谱')
plt.scatter(1 / valid_freq[top_peaks], valid_amplitude[top_peaks], color='red', label='主周期')

# 标注文字
for i in top_peaks:
    x = 1 / valid_freq[i]
    y = valid_amplitude[i]
    plt.text(x, y + 5, f"{x:.1f} 周", ha='center', color='red')

plt.title("STL季节性分量的傅里叶频谱（标注主周期）")
plt.xlabel("周期长度（周）")
plt.ylabel("振幅")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()


import numpy as np
import matplotlib.pyplot as plt

def plot_fft_full(component, title):
    comp = component.dropna()
    n = len(comp)

    fft_vals = np.fft.fft(comp)
    freqs = np.fft.fftfreq(n, d=1)  # 每周采样一次

    amplitude = np.abs(fft_vals)

    # 频率对应周期，跳过 freq=0 避免除零
    valid = freqs != 0
    valid_freqs = freqs[valid]
    valid_amplitudes = amplitude[valid]

    # 横轴周期 = 1/频率（绝对值）
    periods = 1 / np.abs(valid_freqs)

    plt.figure(figsize=(14, 6))
    plt.title(f'{title} 频谱图（傅里叶变换，全部频率点）')
    plt.xlabel('周期 (周)')
    plt.ylabel('振幅')
    plt.scatter(periods, valid_amplitudes, s=10, c='blue')
    plt.xlim(0, max(periods)*1.1)
    plt.grid(True)
    plt.show()

# 趋势分量
plot_fft_full(result.trend, '趋势分量')

# 残差分量
plot_fft_full(result.resid, '残差分量')
