def call(image, tag, repo, branch) {

    sh """
        rm -rf manifests
        git clone -b ${branch} ${repo} manifests
        cd manifests

        sed -i 's|image:.*|image: ${image}:${tag}|' deployment.yaml

        git config user.email "jenkins@devops.com"
        git config user.name "Jenkins"

        git add .
        git commit -m "Update image to ${tag}"
        git push origin ${branch}
    """
}

