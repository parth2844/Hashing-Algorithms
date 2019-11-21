from matplotlib import pyplot as plt
import numpy as np

x=np.loadtxt("x.txt")
y=np.loadtxt("y.txt")

#generating plots for linear probing
plt.loglog(x,y)
plt.xlabel("1/1-Alpha")
plt.ylabel("Number of probes")
plt.title("Linear Probing")
plt.legend()
plt.show()

m, b = np.polyfit(np.log10(x), np.log10(y), 1)

  # Only need two points to define a line
line_xs = np.array([1, 2])
line_ys = m * line_xs + b

  # In order to superimpose plots we need to keep using log-log scale, so we
  # compensate by exponentiation.
plt.loglog(10 * (line_xs), 10 * (line_ys),"--", label='m=%.2f' % m)
plt.xlabel("1/1-Alpha")
plt.ylabel("Number of probes")
plt.title("Linear Probing")
plt.legend()
plt.show()

#generating plots for cuckoo hashing
plt.loglog(x,y)
plt.xlabel("1/1-Alpha")
plt.ylabel("Number of evictions")
plt.title("Cuckoo Hashing")
plt.legend()
plt.show()

m, b = np.polyfit(np.log10(x), np.log10(y), 1)

  # Only need two points to define a line
line_xs = np.array([1, 2])
line_ys = m * line_xs + b

  # In order to superimpose plots we need to keep using log-log scale, so we
  # compensate by exponentiation.
plt.loglog(10 * (line_xs), 10 * (line_ys),"--", label='m=%.2f' % m)
plt.xlabel("1/1-Alpha")
plt.ylabel("Number of evictions")
plt.title("Cuckoo Hashing")
plt.legend()
plt.show()