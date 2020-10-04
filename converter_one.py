import matplotlib.pyplot as plt
import numpy as np
from astropy.io import fits
from astropy.visualization import astropy_mpl_style
from astropy.wcs import WCS
plt.style.use(astropy_mpl_style)
from astropy.utils.data import get_pkg_data_filename
from astropy.coordinates import FK5
from astropy.wcs.utils import celestial_frame_to_wcs

image=get_pkg_data_filename('2.fit')
hdu=fits.open(image)[0]
hdr=hdu.header
wcs=WCS(hdr)
frame = FK5(equinox='J2010')
celestial_frame_to_wcs(frame)
wcs.to_header()
figure=plt.figure()
figure.add_subplot(111,projection=wcs)
plt.imshow(hdu.data, origin='lower', cmap=plt.cm.viridis)
plt.xlabel('Right Ascension')
plt.ylabel('Declination')
