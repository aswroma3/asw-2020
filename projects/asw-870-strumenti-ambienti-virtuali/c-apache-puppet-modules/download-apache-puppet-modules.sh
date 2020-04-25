#!/bin/bash

PUPPET_MODULE_PATH=/vagrant/puppet/modules

function moduleExists {
	MODULE=${PUPPET_MODULE_PATH}/$1
	if [ -e $MODULE ]
	then
		return 0
	else
		return 1
	fi
}

echo "Downloading Puppet modules..."

mkdir -p ${PUPPET_MODULE_PATH}

if ! moduleExists stdlib ; then 
	puppet module install puppetlabs-stdlib --version 6.3.0 --force --modulepath ${PUPPET_MODULE_PATH}
fi 
if ! moduleExists concat ; then 
	puppet module install puppetlabs-concat --version 6.2.0 --force --modulepath ${PUPPET_MODULE_PATH}
fi 
if ! moduleExists apache ; then 
	puppet module install puppetlabs-apache --version 5.4.0 --force --modulepath ${PUPPET_MODULE_PATH}
fi 
