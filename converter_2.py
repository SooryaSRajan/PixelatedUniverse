import matplotlib.pyplot as plt
from astropy.visualization import astropy_mpl_style
plt.style.use(astropy_mpl_style)
from astropy.utils.data import get_pkg_data_filename
from astropy.io import fits
image_file = get_pkg_data_filename('M17.fits')
image_data = fits.getdata(image_file, ext=0)
plt.figure()
plt.imshow(image_data, cmap=plt.cm.viridis)
plt.colorbar()
plt.savefig('M17.jpeg')
