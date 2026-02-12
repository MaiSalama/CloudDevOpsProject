def call(image, tag, repo, branch) {
withCredentials([string(
    credentialsId: 'github-token',
    variable: 'GIT_TOKEN'
)]) 
{
    sh """
        rm -rf manifests
        git clone -b ${branch} https://MaiSalama:${GIT_TOKEN}@github.com/MaiSalama/CloudDevOpsProject-Manifests.git manifests
        cd manifests

        sed -i 's|image:.*|image: ${image}:${tag}|' deployment.yaml

        git config user.email "imaisalama@gmail.com"
        git config user.name "MaiSalama"

        git add .
        git commit -m "Update image to ${tag}"
        git push origin ${branch}
    """
}
}

